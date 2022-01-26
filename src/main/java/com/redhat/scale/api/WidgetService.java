package com.redhat.scale.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    public Widget findById(Long id) throws WidgetNotFoundException {

        Optional<Widget> optional = widgetRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new WidgetNotFoundException();
    }

    public Collection<Widget> findWidgets() {

        return StreamSupport
                .stream(widgetRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Widget saveOrUpdateWidget(final Widget widget) {

        return widgetRepository.save(widget);
    }

    public void deleteWidget(final Long id) throws WidgetNotFoundException {

        Widget widget = findById(id);

        widgetRepository.delete(widget);
    }
}
